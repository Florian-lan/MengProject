import React, { useState, useRef} from "react";
import { Layout, Input, Button, List } from "antd";
// import "antd/dist/antd.css";

const { Header, Content, Footer } = Layout;

const ChatBox = () => {
    const [messages, setMessages] = useState([]);
    const [inputValue, setInputValue] = useState("");
    // const inputRef = useRef(null);

    const handleInputChange = (event) => {
        setInputValue(event.target.value);
    };

    const handleSendMessage = () => {
        // console.log(inputRef.current.input);
        // const inputValue = inputRef.current.input.value?.trim();
        const sent = inputValue;
        if (inputValue) {
            const newMessage = {
                id: messages.length + 1,
                text: inputValue,
                sender: "user",
            };
            console.log(newMessage)
            setMessages([...messages, newMessage]);
            // inputRef.current.input.value = "";
            // console.log(inputRef.current)
            // inputRef.current.focus();
            console.log(messages);
            setInputValue("");
            // 模拟ChatGPT返回的回答
            handleReceiveMessage("这是ChatGPT返回的回答。" + sent);
        }
    };

    const handleReceiveMessage = (text) => {
        const newMessage = {
            id: messages.length + 1,
            text,
            sender: "bot",
        };
        setMessages([...messages, newMessage]);
    };

    const handleKeyDown = (event) => {
        if (event.keyCode === 13) {
            handleSendMessage();
        }
    };

    return (
        <Layout style={{ minHeight: "100vh" }}>
            <Header style={{ background: "#fff", padding: 0 }}>ChatGPT</Header>
            <Content style={{ padding: "0 50px" }}>
                <div style={{ display: "flex", flexDirection: "column-reverse" }}>
                    {console.log(messages)}
                    {messages.map((message) => {
                        console.log(message);
                        return (
                            <div
                                key={message.id}
                                style={{
                                    display: "flex",
                                    justifyContent: message.sender === "user" ? "flex-end" : "flex-start",
                                }}
                            >
                                <div
                                    style={{
                                        backgroundColor: message.sender === "user" ? "#1890ff" : "#f0f0f0",
                                        color: message.sender === "user" ? "#fff" : "#000",
                                        padding: "10px",
                                        borderRadius: "10px",
                                        margin: "10px",
                                        maxWidth: "70%",
                                        wordBreak: "break-word",
                                    }}
                                >
                                    {message.text}
                                </div>
                            </div>
                        )
                    }
                    )}
                </div>
            </Content>
            <Footer style={{ background: "#fff", padding: "10px 50px" }}>
                <Input
                    value={inputValue}
                    onChange={handleInputChange}
                    type="text"
                    // ref={inputRef}
                    onKeyDown={handleKeyDown}
                    placeholder="Enter your message"
                    style={{ marginRight: 10 }}
                />
                <Button type="primary" onClick={handleSendMessage}>
                    Send
                </Button>
            </Footer>
        </Layout>
    );
};

export default ChatBox;
