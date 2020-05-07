import {
	fetchGet, fetchPost,
	fetchDelete, fetchPut,
} from '../asyncRequests';

import {
	setPayment, setPayments,
	updatePayment as updatePaymentAction,
	removePayment as removePaymentAction,
} from '../../redux/actions/payments';

import store from '../../redux/store';

const { dispatch } = store;

export const getAllPayments = () => fetchGet('/api/v1/admin/payments')
	.then((r) => r.json())
	.then((payments) => dispatch(setPayments(payments)));

export const createPayment = (newPayment) => fetchPost('/api/v1/admin/payment', newPayment)
	.then((r) => r.json())
	.then((payment) => dispatch(setPayment(payment)));

export const updatePayment = (newPayment, oldPayment) => {
	const url = `/api/v1/admin/payment/${oldPayment.id}`;
	return fetchPut(url, newPayment)
		.then((r) => r.json())
		.then((payment) => dispatch(updatePaymentAction(payment)));
};

export const removePayment = (payment) => {
	const url = `/api/v1/admin/payment/${payment.id}`;
	return fetchDelete(url, {})
		.then((r) => r.json())
		.then(() => dispatch(removePaymentAction(payment)));
};

export const rentLocker = (payment) => {
	const url = '/api/v1/payments/create';
	return fetchPost(url, payment)
		.then((r) => r.json())
		.then((newPayment) => dispatch(setPayment(newPayment)));
};
