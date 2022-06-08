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
