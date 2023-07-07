import { MantineProvider } from "@mantine/core";
import { Outlet } from "react-router-dom";
import { useSharedColorScheme } from "../hooks/colorScheme";

export default function App() {
  const { colorScheme } = useSharedColorScheme();
  
  return (
    <MantineProvider withGlobalStyles withNormalizeCSS theme={{ colorScheme }}>
      <Outlet/>
    </MantineProvider>
  );
}
