module.exports = {
  root: true,
  env: {
    browser: true,
    es2022: true
  },
  extends: [
    'standard-with-typescript',
    'plugin:tailwindcss/recommended',
    'plugin:react/recommended',
    'next/core-web-vitals',
    'prettier'
  ],
  parserOptions: {
    ecmaFeatures: {
      jsx: true
    },
    ecmaVersion: 'latest',
    sourceType: 'module'
  },
  plugins: ['react', '@typescript-eslint'],
  rules: {
    'react/jsx-uses-react': 'off',
    'react/react-in-jsx-scope': 'off'
  },
  overrides: [
    {
      files: ['*.ts', '*.tsx'],
      parser: '@typescript-eslint/parser'
    }
  ]
};
