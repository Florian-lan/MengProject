import React from 'react';
import ResBox from '../../components/ResBox/ResBox';
import './style.scss';
import { useSelector } from 'react-redux';

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


            </div>

        </>
    )

}

export default Result;