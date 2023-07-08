import {
  Paper,
  Text,
  Container,
  createStyles,
  Center,
  Button,
} from '@mantine/core';
import Logo from '../../components/logo';
import { Link, useNavigate } from 'react-router-dom';
import { useEffect } from 'react';
import { useSharedAuth } from '../../hooks/auth';
import QRCode from 'react-qr-code';

const useStyles = createStyles((_theme) => ({
  container: {
    display: 'flex',
    flexDirection: 'column',
    justifyContent: 'center',
    alignItems: 'stretch',
    paddingBottom: '15rem',
    minHeight: '100dvh',
  }
}));

export default function MyQrCode() {
  const { classes } = useStyles();
  const { authState } = useSharedAuth();
  const navigate = useNavigate();

  useEffect(() => {
    if(!authState) {
      navigate('/');
    }
  }, []);

  if(!authState) return null;

  const url = `${window.location.origin}/transfer/${authState.redmondId}`;
  
  return (
    <Container size="xs" className={classes.container} >
      <Center>
        <Logo/>
      </Center>
      <Paper withBorder shadow="md" p={30} radius="md">
        <Center>
          <Text size={32} mb={10}>Your QR</Text>
        </Center>
        <Center m={30}>
          <QRCode value={url} />
        </Center>
        <Center>
          <Link to="/dashboard">
            <Button mt={30}>Return</Button>
          </Link>
        </Center>
      </Paper>
    </Container>
  );
}