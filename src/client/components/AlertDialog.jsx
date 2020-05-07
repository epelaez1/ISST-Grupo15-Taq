import React from 'react';
import Button from '@material-ui/core/Button';
import Dialog from '@material-ui/core/Dialog';
import DialogActions from '@material-ui/core/DialogActions';
import DialogContent from '@material-ui/core/DialogContent';
import DialogContentText from '@material-ui/core/DialogContentText';
import DialogTitle from '@material-ui/core/DialogTitle';
import { Redirect } from 'react-router-dom';
import { requestRental } from '../utils/api/rentals';

export default function AlertDialog(props) {
	const { newRental } = props;
	const [open, setOpen] = React.useState(true);
	const [redirect, setRedirect] = React.useState(false);

	const handleClickCloseBad = () => {
		setOpen(false);
	};

	const handleCloseGood = (rental) => {
		console.log(rental);
		setOpen(false);
		requestRental(rental);
		setRedirect(true);
	};

	return (
		<div>
			{redirect ? <Redirect to="/" /> : <></>}
			<Dialog
				open={open}
				onClose={handleClickCloseBad}
				aria-labelledby="alert-dialog-title"
				aria-describedby="alert-dialog-description"
			>
				<DialogTitle id="alert-dialog-title">¿Solicitar esta taquilla?</DialogTitle>
				<DialogContent>
					<DialogContentText id="alert-dialog-description">
						¿Quieres confirmar esta taquilla?
					</DialogContentText>
				</DialogContent>
				<DialogActions>
					<Button onClick={() => handleCloseGood(newRental)} color="primary">
						Confirmar
					</Button>
					<Button onClick={handleClickCloseBad} color="primary" autoFocus>
						Cancelar
					</Button>
				</DialogActions>
			</Dialog>
		</div>
	);
}
