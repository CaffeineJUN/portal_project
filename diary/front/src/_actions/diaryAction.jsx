import axios from 'axios'
import * as types from './types'

export const diaryCreate = async dataToSubmit => {
    const request = await axios({
        method: 'post',
        url: '/api/user/diary',
        data: dataToSubmit,
        headers: {'Content-Type': 'multipart/form-data'},
    }).then(res => {
        return res.data
    })

    return {
        type: types.CREATE_DIARY,
        payload: request,
    }
}

export const diaryShow = () => {
    const request = axios({
        method: 'get',
        url: '/api/user/diary',
    }).then(res => {
        return res.data
    })

    return {
        type: types.SHOW_DIARY,
        payload: request,
    }
}
