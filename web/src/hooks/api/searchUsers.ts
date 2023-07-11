import { useSharedAuth } from "../auth";
import { useApiGet } from "./requests/get";

export interface SearchUserData {
    redmondId: string;
}

export function useSearchUsers() {
    const { loading, apiGet } = useApiGet();
    const { authState } = useSharedAuth();

    async function searchUsers(query: string) {
        if(!authState) throw new Error("Not logged in");
        
        const response = await apiGet(`/users?search=${query}`);
        if(!response.ok) throw new Error("Error searching users");

        return await response.json() as [SearchUserData];
    }
    
    return { loading, searchUsers };
}
