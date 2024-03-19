import React from 'react';

const FormLabel: React.FC<{ labelText: string; htmlFor: string }> = ({
  labelText,
  htmlFor
}) => {
  return (
    <label htmlFor={htmlFor} className="block">
      {labelText}
    </label>
  );
};

export default FormLabel;
