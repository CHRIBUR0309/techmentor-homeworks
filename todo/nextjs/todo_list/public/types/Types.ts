interface FailedResponse {
  status: ResponseFailed;
  message: string;
}

type ResponseFailed = 'FAILED';

type ResponseSucceeded = 'SUCCEEDED';

type Status = 'Unprocessed' | 'Proceeding' | 'Finished';

type StatusFilterKey = 'All' | Status;

interface SucceededResponse {
  status: ResponseSucceeded;
}

interface SucceededResponseGetItem extends SucceededResponse {
  result: TodoItem;
}

interface SucceededResponseGetItems extends SucceededResponse {
  results: TodoItem[];
}

interface TodoItem {
  todoId: string;
  title: string;
  status: Status;
  details: string;
}

export type {
  FailedResponse,
  ResponseFailed,
  ResponseSucceeded,
  Status,
  StatusFilterKey,
  SucceededResponse,
  SucceededResponseGetItem,
  SucceededResponseGetItems,
  TodoItem
};
