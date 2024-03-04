type Status = 'Unprocessed' | 'Proceeding' | 'Finished';

type TodoItem = {
  todoId: string;
  title: string;
  status: Status;
  details: string;
};

type StatusFilterKey = 'All' | Status;

export type { Status, StatusFilterKey, TodoItem };
