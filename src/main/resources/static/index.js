const request = axios.create({ baseURL: '/' });

// 请求拦截
request.interceptors.request.use((request) => {
    if (request.headers['no-token']) {
        delete request.headers['no-token'];
        return request;
    } else {
        request.headers['Authorization'] = 'xxx';
    }
    return request;
});

// 响应拦截
request.interceptors.response.use(
    (response) => {
        if (response.status === 401) {
            delete response.headers['x-frame-options'];
        }
        return response.data;
    },
    (err) => Promise.reject(err?.response?.data)
);

// 登录------------------------------------------------------------------------------
const btnLogin = document.getElementById('btnLogin');

const loginData = {
    client_id: 'pan-server-web',
    client_secret: '5EBE2294ECD0E0F08EAB7690D2A6EE69',
    grant_type: 'password',
    username: 'test1',
    password: 'E10ADC3949BA59ABBE56E057F20F883E'
};

btnLogin.onclick = () => {
    request({ method: 'post', url: '/oauth/token', data: loginData })
        .then((res) => {
            console.log(res);
        })
        .catch((err) => {
            console.log('err==>>', err);
        });
};
