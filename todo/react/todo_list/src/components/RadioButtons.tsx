import now from '../../public/utilities/now';
import React from 'react';
import { type Status } from '../../public/types/Types';
import '../index.css';

interface StatusRadioButtonObject {
  buttonLabel: string;
  value: Status;
}

const statusRadioButtons: StatusRadioButtonObject[] = [
  {
    buttonLabel: '未着手',
    value: 'Unprocessed'
  },
  {
    buttonLabel: '進行中',
    value: 'Proceeding'
  },
  {
    buttonLabel: '完了',
    value: 'Finished'
  }
];

const RadioButtons: React.FC<{
  todoId: string;
  status: Status;
  newStatus: Status;
  handleChange: (event: React.ChangeEvent<HTMLInputElement>) => void;
  editFieldRef: React.RefObject<HTMLInputElement>;
}> = ({ todoId, status, newStatus, handleChange, editFieldRef }) => {
  return (
    <>
      {statusRadioButtons.map((radio, i) => {
        const nowDateTime = now();
        return (
          <div className="" key={`statusRadioButtons[${i}]_${nowDateTime}`}>
            <input
              id={`${todoId}_${status}_${nowDateTime}`}
              className=""
              type="radio"
              name="status"
              value={radio.value}
              checked={radio.value === newStatus}
              defaultChecked={radio.value === 'Unprocessed'}
              onChange={handleChange}
              ref={editFieldRef}
            />
            <label className="" htmlFor={`${todoId}_${status}_${nowDateTime}`}>
              {radio.buttonLabel}
            </label>
          </div>
        );
      })}
      ;
    </>
  );
};

export default RadioButtons;
