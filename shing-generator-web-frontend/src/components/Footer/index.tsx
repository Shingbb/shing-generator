import { GithubOutlined } from '@ant-design/icons';
import { DefaultFooter } from '@ant-design/pro-components';
import '@umijs/max';
import React from 'react';

const Footer: React.FC = () => {
  const defaultMessage = '程序员Shing';
  const currentYear = new Date().getFullYear();
  return (
    <DefaultFooter
      style={{
        background: 'none',
      }}
      copyright={`${currentYear} ${defaultMessage}`}
      links={[

        {
          key: 'github',
          title: (
            <>
              <GithubOutlined />Shing源码
            </>
          ),
          href: 'https://github.com/Shingbb',
          blankTarget: true,
        },
      ]}
    />
  );
};
export default Footer;
