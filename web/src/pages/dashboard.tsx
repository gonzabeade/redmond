import { Button, Center, Container, Group, Paper, Text, Title, createStyles } from "@mantine/core";
import { UserData, useGetUser } from "../hooks/api/getUser";
import { useEffect, useState } from "react";
import { MdQrCode2, MdSend, MdLogout } from 'react-icons/md';
import Logo from "../components/logo";
import { useSharedAuth } from "../hooks/auth";
import { useNavigate } from "../router";
import { Link } from "react-router-dom";
import { TransactionData } from "../hooks/api/postTransactions";
import { useGetTransactions } from "../hooks/api/getTransactions";

const useStyles = createStyles((_theme) => ({
  buttonRow: {
    display: 'flex',
    flexDirection: 'row',
    justifyContent: 'space-around',
  },
  link: {
    textDecoration: 'none',
  },
  transaction: {
    transition: "background-color 0.1s ease-in-out",
    ":hover": {
      backgroundColor: "#fafafa",
    }
  }
}));

interface TransactionCardProps {
  transfer: TransactionData;
  redmondId: string;
}
function TransactionCard(props: TransactionCardProps) {
  const { classes } = useStyles();
  const { transfer } = props;

  const out = transfer.source === props.redmondId;

  return (
    <Link to={`/transaction/${transfer.id}`} className={classes.link}>
      <Paper withBorder p={20} mt={10} radius="md" className={classes.transaction}>
        <Group>
          <Text size="lg">{out ? transfer.destination : transfer.source}</Text>
          <Text size="lg" color={out ? 'black' : 'green'} ml="auto" >{out ? '' : '+'} $ {transfer.amount}</Text>
        </Group>
      </Paper>
    </Link>
  );
}

export default function Dashboard() {
  const navigate = useNavigate();
  const { logout, authState } = useSharedAuth();
  const { getUser } = useGetUser();
  const { getTransactions } = useGetTransactions();
  const [user, setUser] = useState<UserData|null>(null);
  const [transactions, setTransactions] = useState<TransactionData[]|null>(null);
  const { classes } = useStyles();

  useEffect(() => {
    if(!authState) navigate("/");

    getUser()
      .then((data) => setUser(data))
      .catch((err) => console.log(err));

    getTransactions()
      .then((data) => setTransactions(data))
      .catch((err) => console.log(err));
  }, [authState]);

  if(!user || !transactions) return null;

  const transactionCards = transactions.map((transaction) => <TransactionCard transfer={transaction} redmondId={user.redmondId} key={transaction.id} />);

  return (
    <Container size={"xs"}>
      <Center><Logo/></Center>
      <Paper withBorder shadow="md" p={30} radius="md">
        <Text size={20}>Redmond ID: {user.redmondId}</Text>
        <Text size={24}>Balance</Text>
        <Title>$ {user.balance.toFixed(2)}</Title>
        <Group mt={16} className={classes.buttonRow}>
          <Link to="/transfer">
            <Button size="sm"><MdSend size={18} /> <Text ml={5}>Transfer</Text></Button>
          </Link>
          <Link to="/qr">
            <Button size="sm"><MdQrCode2 size={18} /> <Text ml={5}>Show QR</Text></Button>
          </Link>
          <Button onClick={logout} size="sm" color="red"><MdLogout size={18} /> <Text ml={5}>Log out</Text></Button>
        </Group>
      </Paper>

      {
        transactions.length > 0 ? (

        <Paper withBorder shadow="md" p={30} mt={30} mb={30} radius="md">
          <Text size={20}>Recent Transactions</Text>
          {transactionCards}
        </Paper>
        
        ) : null
      }

    </Container>
  );
}
