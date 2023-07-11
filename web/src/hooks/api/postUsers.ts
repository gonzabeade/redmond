import { useApiPost } from "./requests/post";

export interface UserForm {
    redmondId: string;
    password: string;
    cbu: string;
    cuil: string;
}

export function usePostUsers() {
    const { loading, apiPost } = useApiPost();

    async function postUsers(form: UserForm) {
        return await apiPost("/users", form);
    }
    
    return { loading, postUsers };
}
