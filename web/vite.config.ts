import { defineConfig } from 'vite';
import react from '@vitejs/plugin-react';
import generouted from '@generouted/react-router/plugin';
import pluginRewriteAll from 'vite-plugin-rewrite-all';


// https://vitejs.dev/config/
export default defineConfig({
  // eslint-disable-next-line @typescript-eslint/no-unsafe-call
  plugins: [react(), generouted(), pluginRewriteAll()],
  resolve: { alias: [{ find: '@', replacement: '/src' }] },
  server: {
    strictPort: true,
    host: true,
    port: 3000
  }
});
