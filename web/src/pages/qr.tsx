import {
  Paper,
  Text,
  Container,
  createStyles,
  Center,
  Button,
} from '@mantine/core';
import Logo from '../components/logo';
import { useEffect } from 'react';
import { useSharedAuth } from '../hooks/auth';
import QRCode from 'react-qr-code';
import { useNavigate } from '../router';

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
  const navigate = useNavigate();
  const { classes } = useStyles();
  const { authState } = useSharedAuth();

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
          <Text size={32} mb={10}>{authState.redmondId}</Text>
        </Center>
        <Center m={30}>
          <QRCode value={url} />
        </Center>
        <Center>
          <Button onClick={() => navigate(-1)} mt={30}>Return</Button>
        </Center>
      </Paper>
    </Container>
  );
}
