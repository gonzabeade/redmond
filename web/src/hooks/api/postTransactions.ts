import { useApiPost } from "./requests/post";

export interface TransactionForm {
    to: string;
    description?: string;
    amount: number;
}

export function usePostTransactions() {
    const { loading, apiPost } = useApiPost();

    async function postTransactions(form: TransactionForm) {
        return await apiPost("/transactions", form);
    }
    
    return { loading, postTransactions };
}
