import axios from 'axios'
import * as types from './types'

export const registUser = async dataToSubmit => {
    const request = await axios.post('/api/register', dataToSubmit).then(res => {
        return res.data
    })

    return {
        type: types.REGIST_USER,
        payload: request,
    }
}

export const loginUser = async dataToSubmit => {
    const request = await axios.post('/api/login', dataToSubmit).then(res => {
        return res.data
    })

    return {
        type: types.LOGIN_USER,
        payload: request,
    }
}

export const updateUser = async dataToSubmit => {
    const request = await axios.put('/api/user/update', dataToSubmit).then(res => {
        return res.data
    })

    return {
        type: types.UPDATE_USER,
        payload: request,
    }
}
