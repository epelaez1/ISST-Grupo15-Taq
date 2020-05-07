import React, { useEffect, useState } from 'react';
import { useSelector, createSelector } from 'react-redux';
import { scaffolds, getRentalsScaffold } from '../utils/tableScaffolds';
import EditableTable from '../components/EditableTable';
import { RentalStates } from '../../server/constants';
import {
	getAllRentals, acceptRequest,
	acceptRenew, endRental,
} from '../utils/api/rentals';
import { getAllLockers } from '../utils/api/lockers';
import { getAllUsers } from '../utils/api/users';

const Requests = () => {
	const [rentalsScaffold, setRentalsScaffold] = useState(scaffolds.rentals);
	const users = useSelector((state) => state.users);
	const lockers = useSelector((state) => state.lockers);
	const pendingAproval = useSelector((state) => state.rentals.filter(
		(rental) => rental.rentalStateId === RentalStates.REQUESTED || rental.rentalStateId === RentalStates.RENEW_REQUESTED,
	));
	const pendingRenew = useSelector((state) => state.rentals.filter(
		(rental) => rental.rentalStateId === RentalStates.RENEW_REQUESTED,
	));

	useEffect(() => {
		getAllRentals();
	}, []);
	
	useEffect(() => {
		getAllUsers();
	}, []);

	useEffect(() => {
		getAllLockers();
	}, []);

	useEffect(() => {
		const rentalScafold = getRentalsScaffold(lockers, users);
		setRentalsScaffold(rentalScafold);
	}, [lockers, users]);

	return (
		<div>
			<EditableTable
				data={pendingAproval}
				title="Solicitudes"
				columns={rentalsScaffold.columns}
				actions={[
					{
						icon: 'done',
						tooltip: 'Aceptar solicitud',
						onClick: (event, rental) => acceptRequest(rental),
						position: 'row',
					},
					{
						icon: 'clear',
						tooltip: 'Rechazar solicitud',
						onClick: (event, rental) => endRental(rental),
						position: 'row',
					},
				]}
			/>

			<br />
			<EditableTable
				data={pendingRenew}
				title="Alquileres pendientes de renovación"
				columns={rentalsScaffold.columns}
				actions={[{
					icon: 'done',
					tooltip: 'Aceptar renovación',
					onClick: (event, rental) => acceptRenew(rental),
					position: 'row',
				}]}
			/>
		</div>
	);
};

export default Requests;
