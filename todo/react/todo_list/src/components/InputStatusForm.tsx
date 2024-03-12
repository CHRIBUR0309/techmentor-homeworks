import React from 'react';
import RadioButtons from './RadioButtons';
import { type Status } from '../../public/types/Types';

const InputStatusForm: React.FC<{
  todoId: string;
  status: Status;
  newStatus: Status;
  handleChange: (event: React.ChangeEvent<HTMLInputElement>) => void;
  ref?: React.RefObject<HTMLInputElement>;
}> = ({ todoId, status, newStatus, handleChange, ref }) => {
  return (
    <fieldset className="mb-4">
      <legend className="block">ステータス</legend>
      <div className="">
        {ref ? (
          <RadioButtons
            todoId={todoId}
            status={status}
            newStatus={newStatus}
            handleChange={handleChange}
            ref={ref}
          />
        ) : (
          <RadioButtons
            todoId={todoId}
            status={status}
            newStatus={newStatus}
            handleChange={handleChange}
          />
        )}
      </div>
    </fieldset>
  );
};

export default InputStatusForm;
