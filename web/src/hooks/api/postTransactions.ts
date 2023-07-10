import { useApiPost } from "./requests/post";

export interface TransactionForm {
    destination: string;
    description?: string;
    amount: number;
}

export interface TransactionData {
    id: string;
    timestamp: Date;
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

        if(response.ok) {
            const json = await response.json() as TransactionData;
            json.timestamp = new Date(json.timestamp);
            return json;
        }
        
        const body = await response.json() as { error: string };        
        throw new Error(body.error);
    }
    
    return { loading, postTransactions };
}
