/*
 * @Author: error: git config user.name && git config user.email & please set dead value or install git
 * @Date: 2022-10-21 00:10:43
 * @LastEditors: error: git config user.name && git config user.email & please set dead value or install git
 * @LastEditTime: 2022-10-21 16:38:14
 * @FilePath: \bookmangement\src\network\manager.js
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
 */
import axios from "axios"
import { Message} from 'element-ui'

// 调用axios.create方法，配置一些属性，返回一个新的axios
const request= axios.create({
    baseURL: "http://localhost:8080/",
    //请求超时时间
    timeout: 2000
})

// 请求拦截
request.interceptors.request.use(
	//config 代表是你请求的一些信息
    config => {
        // 在请求发送之前的操作
        return config
    },
    error => {
        return Promise.reject(error)
    }
)

//  response拦截器 响应拦截器 请求之后的操作
request.interceptors.response.use(
    res => {
        return res.data
    },
    error => {
        return Promise.reject(error)
    }
)

export default request;
