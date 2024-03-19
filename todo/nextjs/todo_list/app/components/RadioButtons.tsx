'use client';

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
  ref?: React.RefObject<HTMLInputElement>;
}> = ({ todoId, status, newStatus, handleChange, ref }) => {
  return (
    <>
      {statusRadioButtons.map((radio, i) => {
        const nowDateTime = now();
        const htmlFor = `${todoId}_${status}_${radio.value}_${nowDateTime}`;
        const className =
          'h-4 w-4 border-gray-300 text-indigo-600 focus:ring-indigo-600';
        return (
          <div
            className="flex items-center"
            key={`statusRadioButtons[${i}]_${nowDateTime}`}
          >
            <input
              id={htmlFor}
              className={className}
              type="radio"
              name="status"
              value={radio.value}
              defaultChecked={radio.value === 'Unprocessed'}
              onChange={handleChange}
              ref={ref}
            />
            <label className="" htmlFor={htmlFor}>
              {radio.buttonLabel}
            </label>
          </div>
        );
      })}
    </>
  );
};

export default RadioButtons;
