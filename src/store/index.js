import { legacy_createStore as createStore } from 'redux';
import { combineReducers, applyMiddleware } from 'redux';
import ImageReducer from './imageStore/reducer'

const { ImageList } = ImageReducer;
const initialMovieInfo = {
    movieInfo: {}
}
const initUserInfo = {
    user_name: '',
    password: ''
}

// store 创建仓库，存放所有的reducer
// 由dispatch发送action，store进行遍历所有的reducer找到对应这个action的处理代码
// createStore(reducer, initState, middleware)
const store = createStore(
    combineReducers({
        ImageList,
    }),
    // reduce函数的初始化state
    {
        ImageList: []
    },
    // 在store中应用中间件
)
// 运行saga中间件，开始监听action
export default store;
