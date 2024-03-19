type Status = 'Unprocessed' | 'Proceeding' | 'Finished';

interface TodoItem {
  todoId: string;
  title: string;
  status: Status;
  details: string;
}

type StatusFilterKey = 'All' | Status;

export type { Status, StatusFilterKey, TodoItem };
