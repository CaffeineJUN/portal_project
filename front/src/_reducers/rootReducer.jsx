import {combineReducers} from 'redux'
import diaryReducer from './diaryReducer'
import userReducer from './userReducer'

const rootReducer = combineReducers({
    user: userReducer,
    diary: diaryReducer,
})

export default rootReducer
