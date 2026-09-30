import http from 'k6/http';
import { check, sleep } from 'k6';

export const options = {
    vus: 10,
    duration: '10s',
};

export default function () {

    const response = http.get(
        'http://localhost:5050/api/v1/usuario'
    );

    check(response, {
        'status es 200': (r) => r.status === 200,
    });

    sleep(1);
}