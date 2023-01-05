/* eslint-disable import/prefer-default-export */
import styled from 'styled-components';

export const Container = styled.div`
  margin-top: 32px;
  position: relative;
`;

export const Header = styled.header`
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-top: 32px;
  border-bottom: 2px solid ${({ theme }) => theme.colors.gray[200]};
  padding-bottom: 16px;
  margin-bottom: 16px;

  strong {
    font-size: 24px;
  }

  a {
    text-decoration: none;
    color: ${({ theme }) => theme.colors.primary.main};
    font-weight: 600;
    border: 2px solid ${({ theme }) => theme.colors.primary.main};
    border-radius: 4px;
    padding: 8px 16px;
    transition: all 0.2s ease-in;

    &:hover {
      color: #FFF;
      background: ${({ theme }) => theme.colors.primary.main};
      transform: scale(1.05);
    }
  }
`;

export const Card = styled.div`
  background: ${({ theme }) => theme.colors.gray[700]};
  color: ${({ theme }) => theme.colors.primary.main};
  padding: 16px;
  border-radius: 4px;
  justify-content: space-between;
  display: flex;
  align-items: center;
  transition: all 0.2s ease-in-out;

  & + & {
    margin-top:16px;
  }

  &:hover {
    box-shadow: 0px 4px 10px rgba(0,0,0,0.1);
    transform: translateY(-1px);
  }

  .info {
    .model {
      display: flex;
      align-items: center;

    }

    span {
      margin: 5px 0;
      display: block;
      font-size: 14px;
      color: ${({ theme }) => theme.colors.gray[200]};
    }

  }

  .actions {
    display: flex;
    flex-direction: column;
    align-items: center;
    gap: 16px;

    button {
      height: 32px;
      width: auto;
    }
  }
`;
