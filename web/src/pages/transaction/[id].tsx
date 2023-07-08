import { Button, Center, Container, Divider, Group, Paper, Text, createStyles } from "@mantine/core";
import Logo from "../../components/logo";
import { Link } from "react-router-dom";
import { useEffect, useState } from "react";
import { TransactionData } from "../../hooks/api/postTransactions";
import { useParams } from "../../router";
import { useGetTransactionById } from "../../hooks/api/getTransactionById";

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

export default function TransactionById() {
  const { id } = useParams("/transaction/:id");
  const { classes } = useStyles();
  const [transaction, setTransaction] = useState<TransactionData|null>(null);
  const { getTransactionById } = useGetTransactionById();

  useEffect(() => {
    getTransactionById(id).then((data) => {
      setTransaction(data);
    }).catch((err) => {
      console.log(err);
    });
  }, []);

  if(!transaction) return null;

  return (
    <Container size={"xs"} className={classes.container} >
      <Center>
        <Logo/>
      </Center>
      <Paper withBorder shadow="md" p={20} px={50} radius="md">
          <Center>
            <Text size={28} mb={16}>Transaction</Text>
          </Center>
          <Group my={10}>
            <Text size={16}>Transaction ID: </Text>
            <Text size={16} ml="auto">{transaction.id}</Text>
          </Group>
          <Divider/>
          <Group my={10}>
            <Text size={16}>From: </Text>
            <Text size={16} ml="auto">{transaction.source}</Text>
          </Group>
          <Divider/>
          <Group my={10}>
            <Text size={16}>To: </Text>
            <Text size={16} ml="auto">{transaction.destination}</Text>
          </Group>
          <Divider/>
          <Group my={10}>
            <Text size={16}>Description: </Text>
            <Text size={16} ml="auto">{transaction.description}</Text>
          </Group>
          <Divider/>
          <Group my={10}>
            <Text size={16}>Amount: </Text>
            <Text size={16} ml="auto">{transaction.amount}</Text>
          </Group>
          <Divider/>
          <Group my={10}>
            <Text size={16}>Status: </Text>
            <Text size={16} ml="auto">{transaction.status}</Text>
          </Group>
          <Center>
            <Link to="/dashboard">
              <Button size="sm" mt={15}>Return</Button>
            </Link>
          </Center>
      </Paper>
    </Container>
  );
}