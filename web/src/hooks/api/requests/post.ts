import { useApiRequest } from "./request";

export function useApiPost() {
    const { loading, apiRequest } = useApiRequest();

    async function apiPost(path: string, body?: unknown) {
        return await apiRequest({
            method: "POST",
            path,
            body
        });
    } 
    
    return { loading, apiPost };
}
