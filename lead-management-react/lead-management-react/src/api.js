import axios from 'axios';
export const api = axios.create({ baseURL: import.meta.env.VITE_API_URL || 'http://localhost:8080' });
api.interceptors.request.use(config => { const token = sessionStorage.getItem('jwt'); if (token) config.headers.Authorization = `Bearer ${token}`; return config; });
export const authApi = { login: data => api.post('/api/auth/login', data), register: data => api.post('/api/auth/register', data) };
export const leadApi = { all: () => api.get('/api/leads'), one: id => api.get(`/api/leads/${id}`), create: data => api.post('/api/leads', data), update: (id, data) => api.put(`/api/leads/${id}`, data), remove: id => api.delete(`/api/leads/${id}`) };
export const dashboardApi = { get: () => api.get('/api/dashboard') };
export function errorMessage(e) { const data = e.response?.data; return (typeof data === 'string' && data) || data?.message || data?.detail || (e.response?.status === 403 ? 'Access denied. Please log in again.' : e.message) || 'Request failed'; }
