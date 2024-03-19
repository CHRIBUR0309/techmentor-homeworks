'use client';

import React from 'react';
import InputDetailsForm from './InputDetailsForm';
import InputTitleForm from './InputTitleForm';

const InputTextForm: React.FC<{
  isTitle: boolean;
  htmlFor: string;
  id: string;
  value: string;
  onChange: React.ChangeEventHandler<HTMLInputElement>;
  ref?: React.RefObject<HTMLInputElement>;
}> = ({ isTitle, htmlFor, id, value, onChange, ref }) => {
  return (
    <div className="mb-4">
      {isTitle ? (
        <InputTitleForm
          labelText="タイトル"
          htmlFor={htmlFor}
          id={id}
          value={value}
          onChange={onChange}
          ref={ref}
        />
      ) : (
        <InputDetailsForm
          labelText="詳細"
          htmlFor={htmlFor}
          id={id}
          value={value}
          onChange={onChange}
          ref={ref}
        />
      )}
    </div>
  );
};

export default InputTextForm;
