import {
  TextInput,
  PasswordInput,
  Anchor,
  Paper,
  Text,
  Container,
  Button,
  createStyles,
} from '@mantine/core';
import {useForm} from '@mantine/form';
import Logo from '../components/logo';
import { Link, useNavigate } from 'react-router-dom';
import { useEffect, useRef, useState } from 'react';
import { useSharedAuth } from '../hooks/auth';

const useStyles = createStyles((_theme) => ({
  container: {
    display: 'flex',
    flexDirection: 'column',
    justifyContent: 'center',
    paddingBottom: '15rem',
    minHeight: '100dvh',
    maxWidth: "400px",
  }
}));

export default function Index() {
  const { classes } = useStyles();
  const { authState, login } = useSharedAuth();
  const [error, setError] = useState<string|null>(null);
  const navigate = useNavigate();

  const buttonRef = useRef<HTMLButtonElement>(null);

  const form = useForm({
    initialValues: {
      redmondId: '',
      password: ''
    },

    validate: {
      redmondId: (value) => (/^[a-z]([a-z]|\.)+[a-z]$/.test(value) ? null : 'Invalid Redmond ID'),
      password: (value) => (value.length >= 8 && value.length <= 32) ? null : 'Password must be between 8 and 32 characters',
    }
  });
  
  useEffect(() => {
    if(authState) {
      navigate('/dashboard');
    }
  }, []);

  if(authState) return null;

  async function handleLogin(input: { redmondId: string, password: string }) {
    if(buttonRef.current) buttonRef.current.disabled = true;
    
    try {
      const success = await login(input.redmondId, input.password);
      if(success) {
        navigate('/dashboard');
      } else {
        form.setFieldError('redmondId', ' ');
        form.setFieldError('password', ' ');
        setError('Invalid Redmond ID or password');
      }
    } catch(err) {
      console.error(err);
      setError('An error occurred while logging in. Please try again later.');
    }

    if(buttonRef.current) buttonRef.current.disabled = false;
  }

  let errorText;
  if(error) {
    errorText = <Text color="red" size="sm" align="center" mb={20}>{error}</Text>;
  }
  
  return (
    <Container className={classes.container}>
      <div style={{alignSelf: 'center'}}>
        <Logo/>
      </div>
      <Text color="dimmed" size="sm" align="center" mt={5}>
        Do not have an account yet?{' '}
        <Link to="/register">
          <Anchor size="sm" component="button">
            Create account
          </Anchor>
        </Link>
      </Text>

      <Paper withBorder shadow="md" p={30} mt={30} radius="md">
        { errorText }

        <form onSubmit={form.onSubmit((input) => void handleLogin(input))}>  
          <TextInput label="Redmond ID" placeholder="your.redmond.id" required {...form.getInputProps('redmondId')} />
          <PasswordInput label="Password" placeholder="Your password" required mt="md" {...form.getInputProps('password')} />
          <Button ref={buttonRef} type='submit' fullWidth mt="xl">
            Sign in
          </Button>
        </form>
      </Paper>
    </Container>
  );
}