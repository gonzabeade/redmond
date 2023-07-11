import { useApiRequest } from "./request";

export function useApiPatch() {
    const { loading, apiRequest } = useApiRequest();

    async function apiPatch(path: string, body?: unknown) {
        return await apiRequest({
            method: "PATCH",
            path,
            body
        });
    }
    
    return { loading, apiPatch };
}
