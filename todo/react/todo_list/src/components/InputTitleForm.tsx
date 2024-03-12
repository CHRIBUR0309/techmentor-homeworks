import React from 'react';
import FormLabel from './FormLabel';

const InputTitleForm: React.FC<{
  labelText: string;
  htmlFor: string;
  id: string;
  value: string;
  onChange: React.ChangeEventHandler<HTMLInputElement>;
  ref?: React.RefObject<HTMLInputElement>;
}> = ({ labelText, htmlFor, id, value, onChange, ref }) => {
  const className =
    'block w-1/2 rounded-md border-0 py-1.5 ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600';
  const placeholder = 'Todoタイトル';
  return (
    <>
      <FormLabel labelText={labelText} htmlFor={htmlFor} />
      {ref ? (
        <input
          type="text"
          id={id}
          className={className}
          title="text"
          autoComplete="off"
          value={value}
          placeholder={placeholder}
          onChange={onChange}
          ref={ref}
        />
      ) : (
        <input
          type="text"
          id={id}
          className={className}
          title="text"
          autoComplete="off"
          value={value}
          placeholder={placeholder}
          onChange={onChange}
        />
      )}
    </>
  );
};

export default InputTitleForm;
