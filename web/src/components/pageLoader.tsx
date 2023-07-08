import { Loader as MantineLoader, createStyles } from '@mantine/core';

const useStyles = createStyles((_theme) => ({
  container: {
    width: '100dvw',
    height: '100dvh',
    display: 'flex'
  }
}));

export default function PageLoader() {
  const { classes } = useStyles();
  
  return (
      <div className={classes.container} >
        <MantineLoader size={'xl'} style={{margin: 'auto'}} />
      </div>
  );
}
