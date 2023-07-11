import { createStyles } from "@mantine/core";


const useStyles = createStyles((theme) => ({
  logo: {
    fontFamily: `Montserrat, ${theme.fontFamily || ''} !important`,
    fontSize: '2rem',
    fontWeight: 300,
    userSelect: "none",
  }
}));

export default function Logo() {
  const { classes } = useStyles();
  
  return (
    <h1 className={classes.logo}>
      REDMOND
    </h1>
  );
}