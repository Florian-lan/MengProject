import React, { useState, useRef } from "react";
import "./style.scss";
import { InboxOutlined } from '@ant-design/icons';
import { Button, message, Upload } from 'antd';
import ImgCrop from 'antd-img-crop';

import axios from 'axios';


const UploadImg = () => {
    // for test
    const testImgInfo = {
        uid: '-1',
        name: 'image.png',
        status: 'done',
        url: 'forInterfaceURL',
    }
    const uploadRef = useRef(null);
    const [fileList, setFileList] = useState([]);
    const [imageUrl, setImageUrl] = useState('');
    const [loading, setLoading] = useState(false);
    const { Dragger } = Upload;

    // Server API #TODO
    const SERVER_URL = 'http://localhost:8080'
    const UPLOAD_URL = `${SERVER_URL}/upload`

    const onPreview = async (file) => {
        let src = file.url;
        if (!src) {
            src = await new Promise((resolve) => {
                const reader = new FileReader();
                reader.readAsDataURL(file.originFileObj);
                reader.onload = () => resolve(reader.result);
            });
        }
        const image = new Image();
        image.src = src;
        const imgWindow = window.open(src);
        imgWindow?.document.write(image.outerHTML);
    }
    const handleUpload = function (options) {
        const { file, onSuccess, onError } = options;
        if (!beforeUpload) return;
        setLoading(true);
        console.log(file)
        // 创建 FormData 对象，用于发送图片数据
        const formData = new FormData();
        formData.append('image', file);
        console.log(formData.get('image'))
        // fileList?.forEach((file) => {

        //     formData.append('files[]', file);
        // });

        try {
            // send the image to server to process
            axios.post({ UPLOAD_URL }, formData, {
                headers: { 'Content-Type': 'multipart/form-data' }
            }).then(response => {
                onSuccess(response, file);
            })
                .catch(error => {
                    onError(error);
                });
            // const imageUrls = response.data;
            // console.log(response);
            // // TODO process imgs response by server
            // setImageUrl(imageUrls);
        } catch (error) {
            message.error('上传图片失败，请重试!');
        } finally {
            setLoading(false);
        }
    }
    const beforeUpload = function (file) {
        console.log(file);
        const isJpgOrPng = file.type === 'image/jpeg' || file.type === 'image/png';
        if (!isJpgOrPng) {
            message.error('只能上传 JPG/PNG 格式的图片!');
            return false;
        }
        const isLt2M = file.size / 1024 / 1024 < 2;
        if (!isLt2M) {
            message.error('图片大小不能超过 2MB!');
            return false;
        }
        return true;
    }
    const handleChange = (info) => {

        const { file, fileList } = info;
        // console.log("onChange", fileList)
        // const { status, response } = file;
        // console.log(response);
        // if (status === 'done') {
        //     // 文件上传成功，可以从 response 中获取服务器端响应的数据
        //     console.log('服务器端响应数据：', response);
        //     setLoading(false);
        //     // 进行处理响应数据的逻辑
        //     const imageUrls = response.data;

        //     // TODO process imgs response by server
        //     setImageUrl(imageUrls);
        // } else if (status === 'error') {
        //     // 文件上传失败，可以从 info.fileList 中获取错误信息
        //     console.log('上传失败：', fileList);
        //     // 进行错误处理的逻辑
        // }
        setFileList(fileList)

    };
    const handleRemove = function (file) {
        const index = fileList.indexOf(file);
        const newFileList = fileList.slice();
        newFileList.splice(index, 1);
        setFileList(newFileList);
    }

    const handleUploadClick = function () {
        // 调用 Upload 组件的 upload 方法，触发上传操作
        const uploadInstance = uploadRef.current;
        if (uploadInstance) {
            uploadInstance.upload();
            setLoading(true)
        }
    }
    const handleTest = function () {
        const data = 'hello world';
        axios.post('http://localhost:8080/test', data)
            .then(response => {
                // 处理响应结果
                console.log(response)
            })
            .catch(error => {
                // 处理错误
            });
    }

    return (
        <>

            <ImgCrop rotationSlider>
                <Upload
                    name='uploadFile'
                    // action={UPLOAD_URL}
                    // multiple={true}
                    // ref={uploadRef}
                    listType="picture-card"
                    fileList={fileList}
                    onRemove={handleRemove}
                    // beforeUpload={(file) => {
                    //     // console.log(file);
                    //     // handleUpload(file);
                    //     return false; // stop default upload event
                    // }}
                    onChange={handleChange}
                    onPreview={onPreview}
                    customRequest={handleUpload}
                >
                    {fileList.length < 5 && '+ Upload'}
                </Upload>

            </ImgCrop>
            <Button
                type="primary"
                onClick={handleUploadClick}
                disabled={fileList.length === 0}
                loading={loading}
                style={{ marginTop: 16 }}
            >
                {loading ? 'Uploading' : 'Start Upload'}
            </Button>
            {loading && <div>正在上传图片，请稍候...</div>}
            {imageUrl && (
                <div>
                    {imageUrl?.map((url, index) => (
                        <img key={index} src={url} alt={`processed image ${index}`} />
                    ))}
                </div>
            )}
            <Button
                onClick={handleTest}>
                test
            </Button>


        </>


    )
}


export default UploadImg;