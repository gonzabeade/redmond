import {
  TextInput,
  PasswordInput,
  Paper,
  Text,
  Container,
  Button,
  createStyles,
} from '@mantine/core';
import {useForm} from '@mantine/form';
import Logo from '../components/logo';
import { useNavigate } from 'react-router-dom';
import { useEffect, useRef, useState } from 'react';
import { useSharedAuth } from '../hooks/auth';
import { UserForm, usePostUsers } from '../hooks/api/postUsers';

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

export default function Register() {
  const { classes } = useStyles();
  const { authState, login } = useSharedAuth();
  const [error, setError] = useState<string|null>(null);
  const { postUsers } = usePostUsers();
  const navigate = useNavigate();

  const buttonRef = useRef<HTMLButtonElement>(null);

  const form = useForm({
    initialValues: {
      redmondId: '',
      cuil: '',
      cbu: '',
      password: '',
      confirmPassword: '',
    },

    validate: {
      redmondId: (value) => (/^[a-z]([a-z]|\.)+[a-z]$/.test(value) ? null : 'Invalid Redmond ID'),
      cuil: (value) => (/^\d{11}$/.test(value) ? null : 'Invalid CUIL'),
      cbu: (value) => (/^\d{22}$/.test(value) ? null : 'Invalid CBU'),
      password: (value) => (value.length >= 8 && value.length <= 32) ? null : 'Password must be between 8 and 32 characters',
      confirmPassword: (value, values) => (value === values.password) ? null : 'Passwords do not match',
    }
  });

  useEffect(() => {
    if(authState) {
      navigate('/dashboard');
    }
  });

  if(authState) return null;

  function handleRegister(input: UserForm) {
    async function asyncRegister() {
      if(buttonRef.current) buttonRef.current.disabled = true;
      setError(null);

      try {
        const response = await postUsers(input);
        if(response.ok) {
          await login(input.redmondId, input.password);
          navigate('/dashboard');
        } else {
          const data = await response.json() as { error?: string };

          if(data.error && data.error.includes('Bank')) {
            form.setFieldError('cbu', ' ');
            form.setFieldError('cuil', ' ');
          }

          setError(data.error ?? 'An error occurred while creating user. Please try again later.');
        }
      } catch(err) {
        console.error(err);
        setError('An error occurred while creating user. Please try again later.');
      }

      if(buttonRef.current) buttonRef.current.disabled = false;
    }
    void asyncRegister();
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

      <Paper withBorder shadow="md" p={30} mt={30} radius="md">
        { errorText }

        <form onSubmit={form.onSubmit(handleRegister)}>
          <TextInput label="Redmond ID" placeholder="your.redmond.id" required {...form.getInputProps('redmondId')} />
          <TextInput label="CUIL" placeholder="Your CUIL" mt="md" required {...form.getInputProps('cuil')} />
          <TextInput label="CBU" placeholder="Your CBU"  mt="md" required {...form.getInputProps('cbu')} />
          <PasswordInput label="Password" placeholder="Your password" mt="md" required {...form.getInputProps('password')} />
          <PasswordInput label="Confirm password" placeholder="Confirm your password" mt="md" required {...form.getInputProps('confirmPassword')} />
          <Button ref={buttonRef} type='submit' fullWidth mt="xl">
            Register
          </Button>
        </form>
      </Paper>
    </Container>
  );
}