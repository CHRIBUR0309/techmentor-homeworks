import {
  type FailedResponse,
  type Status,
  type StatusFilterKey,
  type SucceededResponse,
  type SucceededResponseGetItem,
  type SucceededResponseGetItems
} from '@/public/types/Types';
import axios, { type AxiosError } from 'axios';

const axiosInstance = axios.create({
  baseURL: 'http://localhost:8080',
  timeout: 5000
});

export const getAllItems = async (): Promise<
  SucceededResponseGetItems | FailedResponse
> => {
  return await axiosInstance
    .get<SucceededResponseGetItems>('/get')
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
  return await axiosInstance
    .get<SucceededResponseGetItem>(`/get/todoId_${todoId}`)
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
  if (status === 'All') {
    return await getAllItems();
  }
  return await axiosInstance
    .get<SucceededResponseGetItems>(`/get/status_${status}`)
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
  return await axiosInstance
    .post<SucceededResponse>(`/post/${todoId}/${title}/${status}`)
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
  return await axiosInstance
    .post<SucceededResponse>(`/post/${todoId}/${title}/${status}/${details}`)
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
  return await axiosInstance
    .patch<SucceededResponse>(`/patch/${todoId}/${title}/${status}/${details}`)
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
  return await axiosInstance
    .delete<SucceededResponse>(`/delete/${todoId}`)
    .then((res) => res.data)
    .catch((err: AxiosError<FailedResponse>) => {
      const data: FailedResponse = err.response?.data ?? {
        status: 'FAILED',
        message: err.message
      };
      return data;
    });
};
