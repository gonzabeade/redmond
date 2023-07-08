import { useApiPost } from "./requests/post";

export interface TransactionForm {
    destination: string;
    description?: string;
    amount: number;
}

export interface TransactionData {
    id: string;
    source: string;
    destination: string;
    description?: string;
    amount: number;
    status: "pending" | "approved" | "rejected";
}

export function usePostTransactions() {
    const { loading, apiPost } = useApiPost();

    async function postTransactions(form: TransactionForm) {
        const response = await apiPost("/transactions", form);
        if(response.ok) return await response.json() as TransactionData;
        if(response.status === 400) throw new Error("Invalid transaction data");

        throw new Error("Error creating transaction");
    }
    
    return { loading, postTransactions };
}
