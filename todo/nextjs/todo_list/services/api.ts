import {
  type FailedResponse,
  type Status,
  type StatusFilterKey,
  type SucceededResponse,
  type SucceededResponseGetItem,
  type SucceededResponseGetItems
} from '@/public/types/Types';
import axios, { type AxiosError } from 'axios';

const apiUrl = 'http://localhost:8080';

export const getAllItems = async (): Promise<
  SucceededResponseGetItems | FailedResponse
> => {
  return await axios
    .get<SucceededResponseGetItems>(`${apiUrl}/get`)
    .then((res) => res.data)
    .catch((err: AxiosError<FailedResponse>) => {
      const data: FailedResponse = err.response?.data ?? {
        status: 'FAILED',
        message: err.message
      };
      return data;
    });
};

export const getItemById = async (
  todoId: string
): Promise<SucceededResponseGetItem | FailedResponse> => {
  return await axios
    .get<SucceededResponseGetItem>(`${apiUrl}/get/todoId_${todoId}`)
    .then((res) => res.data)
    .catch((err: AxiosError<FailedResponse>) => {
      const data: FailedResponse = err.response?.data ?? {
        status: 'FAILED',
        message: err.message
      };
      return data;
    });
};

export const getItemsByStatus = async (
  status: StatusFilterKey
): Promise<SucceededResponseGetItems | FailedResponse> => {
  return await axios
    .get<SucceededResponseGetItems>(`${apiUrl}/get/status_${status}`)
    .then((res) => res.data)
    .catch((err: AxiosError<FailedResponse>) => {
      const data: FailedResponse = err.response?.data ?? {
        status: 'FAILED',
        message: err.message
      };
      return data;
    });
};

export const insertItemWithoutDetails = async (
  todoId: string,
  title: string,
  status: Status
): Promise<SucceededResponse | FailedResponse> => {
  return await axios
    .post<SucceededResponse>(`${apiUrl}/post/${todoId}/${title}/${status}`)
    .then((res) => res.data)
    .catch((err: AxiosError<FailedResponse>) => {
      const data: FailedResponse = err.response?.data ?? {
        status: 'FAILED',
        message: err.message
      };
      return data;
    });
};

export const insertItemWithDetails = async (
  todoId: string,
  title: string,
  status: Status,
  details: string
): Promise<SucceededResponse | FailedResponse> => {
  return await axios
    .post<SucceededResponse>(
      `${apiUrl}/post/${todoId}/${title}/${status}/${details}`
    )
    .then((res) => res.data)
    .catch((err: AxiosError<FailedResponse>) => {
      const data: FailedResponse = err.response?.data ?? {
        status: 'FAILED',
        message: err.message
      };
      return data;
    });
};

export const updateItem = async (
  todoId: string,
  title: string,
  status: Status,
  details: string
): Promise<SucceededResponse | FailedResponse> => {
  return await axios
    .patch<SucceededResponse>(
      `${apiUrl}/patch/${todoId}/${title}/${status}/${details}`
    )
    .then((res) => res.data)
    .catch((err: AxiosError<FailedResponse>) => {
      const data: FailedResponse = err.response?.data ?? {
        status: 'FAILED',
        message: err.message
      };
      return data;
    });
};

export const deleteItem = async (
  todoId: string
): Promise<SucceededResponse | FailedResponse> => {
  return await axios
    .delete<SucceededResponse>(`${apiUrl}/delete/${todoId}`)
    .then((res) => res.data)
    .catch((err: AxiosError<FailedResponse>) => {
      const data: FailedResponse = err.response?.data ?? {
        status: 'FAILED',
        message: err.message
      };
      return data;
    });
};
