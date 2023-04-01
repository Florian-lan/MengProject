import React, { useState } from "react";
import './style.scss';
import { Input } from 'antd';
import { SearchOutlined } from '@ant-design/icons';
import { useLocation, useNavigate } from "react-router-dom";
import { AppstoreOutlined, MailOutlined, SettingOutlined } from '@ant-design/icons';
import { Menu } from 'antd';



const { Search } = Input;
const { SubMenu } = Menu;

const items = [

    {
        label: 'Navigation Three - Submenu',
        key: 'SubMenu',
        icon: <SettingOutlined />,
        children: [
            {
                type: 'group',
                label: 'Item 1',
                children: [
                    {
                        label: 'Option 1',
                        key: 'setting:1',
                    },
                    {
                        label: 'Option 2',
                        key: 'setting:2',
                    },
                ],
            },
            {
                type: 'group',
                label: 'Item 2',
                children: [
                    {
                        label: 'Option 3',
                        key: 'setting:3',
                    },
                    {
                        label: 'Option 4',
                        key: 'setting:4',
                    },
                ],
            },
        ],
    },
    {
        label: (
            <a href="#TODO" target="_blank" rel="noopener noreferrer">
                Related Docs
            </a>
        ),
        key: 'docs',
    },
];
const Header = () => {
    const [current, setCurrent] = useState('SubMenu');
    const onClick = (e) => {
        console.log('click ', e);
        setCurrent(e.key);
    };
    return (
        <Menu onClick={onClick} selectedKeys={[current]} mode="horizontal" items={items} />
    )

};



export default Header;
