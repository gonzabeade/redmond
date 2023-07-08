import { Center, Container, Paper, TextInput, Text, Button, createStyles } from "@mantine/core";
import { useForm } from "@mantine/form";
import { useEffect, useState } from "react";
import { usePostTransactions } from "../../hooks/api/postTransactions";
import { useNavigate, useParams } from "../../router";
import { useSharedAuth } from "../../hooks/auth";
import { useGetTransactionById } from "../../hooks/api/getTransactionById";
import PageLoader from "../../components/pageLoader";
import Logo from "../../components/logo";

const useStyles = createStyles((_theme) => ({
  container: {
    minHeight: '100dvh',
    maxWidth: '500px',
    display: 'flex',
    flexDirection: 'column',
    justifyContent: 'center',
    alignContent: 'center',
    paddingBottom: '12rem'
  }
}));

interface TransferData {
  destination: string;
  amount: string;
  description: string;
}

export default function TransferToId() {
  const { id } = useParams("/transfer/:id?");
  const navigate = useNavigate();
  const { postTransactions } = usePostTransactions();
  const { getTransactionById } = useGetTransactionById();
  const { authState } = useSharedAuth();
  const { classes } = useStyles();
  const [error, setError] = useState<string|null>(null);
  const [loading, setLoading] = useState<boolean>(false);

  const form = useForm({
    initialValues: {
      destination: id ? id : "",
      amount: "",
      description: ""
    },
    validate: {
      destination: (value) => (/^[a-z]([a-z]|\.)+[a-z]$/.test(value) ? null : 'Invalid Destination'),
      amount: (value) => (/^\d{1,8}(\.\d{1,2})?$/.test(value) ? null : 'Invalid Amount')
    }
  });

  useEffect(() => {
    if(!authState) navigate("/");
  }, [authState]);

  async function onSend(input: TransferData) {
    setError(null);
    setLoading(true);
    try {
      const response = await postTransactions({
        amount: parseFloat(input.amount),
        description: input.description,
        destination: input.destination
      });

      await new Promise((resolve) => setTimeout(resolve, 1000));
      
      const transaction = await getTransactionById(response.id);
      navigate('/transaction/:id', { params: { id: transaction.id } });
    } catch(err) {
      console.log(err);
      setError((err as Error).message);
    }
    setLoading(false);
  }

  let errorText;
  if(error) {
    errorText = <Text color="red" mt={20}>{error}</Text>;
  }

  return loading ? (<PageLoader/>) : (
    <Container size={"xs"} className={classes.container} >
      <Center>
        <Logo/>
      </Center>
      <Paper withBorder shadow="md" p={30} radius="md">
          <Center>
            <Text size={32} mb={20}>Transfer { id ? 'to' : ''} {id}</Text>
          </Center>
          {errorText}
        <form onSubmit={form.onSubmit((input) => void onSend(input))}>
          <TextInput disabled={!!id} label="Destination" placeholder="their.redmond.id" required {...form.getInputProps('destination')} />
          <TextInput label="Amount" placeholder="0.00" required mt={20} {...form.getInputProps('amount')} />
          <TextInput label="Description" placeholder="Optional" mt={20} {...form.getInputProps('description')} />
          <Center>
            <Button disabled={loading} type="submit" mt={30}>Send</Button>
          </Center>
        </form>
      </Paper>
    </Container>
  );
}
