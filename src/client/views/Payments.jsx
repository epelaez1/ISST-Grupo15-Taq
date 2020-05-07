import React, { useEffect, useState } from 'react';
import { useSelector } from 'react-redux';
import { RentalStates } from '../../server/constants';
import { scaffolds, getRentalsScaffold } from '../utils/tableScaffolds';
import {
	getAllPaymentMethods, createPaymentMethod,
	updatePaymentMethod, removePaymentMethod,
} from '../utils/api/paymentMethods';
import { rentLocker, getAllPayments } from '../utils/api/payments';
import { getAllRentals } from '../utils/api/rentals';
import { getAllLockers } from '../utils/api/lockers';
import { getAllUsers } from '../utils/api/users';
import EditableTable from '../components/EditableTable';

const Payments = () => {
	const paymentMethodsScaffold = scaffolds.paymentMethods;
	const paymentsScaffold = scaffolds.payments;
	const [rentalsScaffold, setRentalsScaffold] = useState(scaffolds.rentals);
	const users = useSelector((state) => state.users);
	const lockers = useSelector((state) => state.lockers);
	const payments = useSelector((state) => state.payments);
	const paymentMethods = useSelector((state) => state.paymentMethods);
	const pendingPayment = useSelector((state) => state.rentals.filter(
		(rental) => rental.rentalStateId === RentalStates.RESERVED,
	));
	useEffect(() => {
		getAllPaymentMethods();
	}, []);
	useEffect(() => {
		getAllRentals();
	}, [payments]);
	useEffect(() => {
		getAllUsers();
	}, []);
	useEffect(() => {
		getAllPayments();
	}, []);
	useEffect(() => {
		getAllLockers();
	}, []);
	useEffect(() => {
		setRentalsScaffold(getRentalsScaffold(lockers, users));
	}, [lockers, users]);

	return (
		<div>
			<br />
			<EditableTable
				data={pendingPayment}
				title="Alquileres pendientes de pago"
				columns={rentalsScaffold.columns}
				actions={[{
					icon: 'euro',
					tooltip: 'Tramitar alquiler',
					onClick: (event, rental) => rentLocker({
						quantity: 10,
						userId: rental.userId,
						lockerId: rental.lockerId,
						rentalId: rental.id,
						paymentMethodId: 1,
					}),
					position: 'row',
				}]}
			/>
			<br />
			<EditableTable
				data={payments}
				columns={paymentsScaffold.columns}
				title={paymentsScaffold.title}
				actions={[]}
			/>
			<br />
			
			<EditableTable
				data={paymentMethods}
				title={paymentMethodsScaffold.title}
				columns={paymentMethodsScaffold.columns}
				create={createPaymentMethod}
				update={updatePaymentMethod}
				remove={removePaymentMethod}
				actions={[]}
				editable
				showEmpty
			/>
		</div>

	);
};

export default Payments;
