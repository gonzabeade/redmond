import { useState } from "react";
import { useBetween } from "use-between";

const useColorScheme = () => {
    const [colorScheme, setColorSchemeRaw] = useState<'light' | 'dark'>(() => {
        // Get colorscheme from localstorage if it exists
        const stored = localStorage.getItem('colorScheme') as 'light' | 'dark';
        if (stored) return stored;
        return 'light';
    });

    function setColorScheme(scheme: 'light' | 'dark') {
        setColorSchemeRaw(scheme);
        localStorage.setItem('colorScheme', scheme);
    }

    function toggleColorScheme() {
        setColorScheme(colorScheme === 'dark' ? 'light' : 'dark');
    }

    return {
        colorScheme,
        setColorScheme, 
        toggleColorScheme
    };
};

export const useSharedColorScheme = () => useBetween(useColorScheme);
