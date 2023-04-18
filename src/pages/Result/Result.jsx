import React from 'react';
import ResBox from '../../components/ResBox/ResBox';
import './style.scss';
import { useSelector } from 'react-redux';
import ChatBox from '../../components/ChatBox/ChatBox';

const Result = () => {
    // const imageList = useSelector(state => {
    //     console.log(state.ImageList);
    //     return state.ImageList.imageList;

    // })
    return (
        <>
            <div className="resBody">
                {/* {imageList} */}
                test
                <ResBox />
                <div className="chat">
                    <ChatBox/>
                </div>


            </div>

        </>
    )

}

export default Result;