import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import AppBar from '@material-ui/core/AppBar';
import Toolbar from '@material-ui/core/Toolbar';
import Typography from '@material-ui/core/Typography';
import IconButton from '@material-ui/core/IconButton';
import MenuIcon from '@material-ui/icons/Menu';
import Switch from '@material-ui/core/Switch';
import FormControlLabel from '@material-ui/core/FormControlLabel';
import FormGroup from '@material-ui/core/FormGroup';
import MenuItem from '@material-ui/core/MenuItem';
import Menu from '@material-ui/core/Menu';
import { Link as RouterLink } from 'react-router-dom';

import Button from '@material-ui/core/Button';
import SupervisorAccountIcon from '@material-ui/icons/SupervisorAccount';
import Divider from '@material-ui/core/Divider';
import Fade from '@material-ui/core/Fade';
import { useSelector, useDispatch } from 'react-redux';
import { makeAdmin } from '../redux/actions/users';

export default function MenuAppBar(props) {
	const {
		handleDrawerToggle, classes,
	} = props;
	const [anchorEl, setAnchorEl] = React.useState(null);
	const dispatch = useDispatch();
	const open = Boolean(anchorEl);
	const loggedUser = useSelector((state) => state.loggedUser);
	const auth = loggedUser.isAdmin;
	const handleChange = () => {
		dispatch(makeAdmin());
	};

	const handleMenu = (event) => {
		setAnchorEl(event.currentTarget);
	};

	const handleClose = () => {
		setAnchorEl(null);
	};

	return (
		<div className={classes.root}>
			<AppBar position="fixed" className={classes.appBar}>
				<Toolbar>
					<IconButton
						color="inherit"
						aria-label="open drawer"
						edge="start"
						onClick={handleDrawerToggle}
						className={classes.menuButton}
					>
						<MenuIcon />
					</IconButton>
					<Typography variant="h6" className={classes.title} style={{ color: 'inherit', textDecoration: 'inherit' }} component={RouterLink} to="/">
						Taquillas UPM
					</Typography>
					<Typography variant="h6" style={{ color: 'inherit', textDecoration: 'inherit' }}>
						{loggedUser.name}
					</Typography>

					{auth && (
						<div>
							<Button
								aria-label="account of current user"
								aria-controls="menu-appbar"
								aria-haspopup="true"
								onClick={handleMenu}
								color="inherit"
							>
								Panel de control
								<SupervisorAccountIcon />
							</Button>
							<Menu
								id="menu-appbar"
								TransitionComponent={Fade}
								anchorEl={anchorEl}
								anchorOrigin={{
									vertical: 'top',
									horizontal: 'center',
								}}
								keepMounted
								transformOrigin={{
									vertical: 'top',
									horizontal: 'center',
								}}
								open={open}
								onClose={handleClose}
							>

								<MenuItem onClick={handleClose} component={RouterLink} to="/">Añadir localización</MenuItem>
								<MenuItem onClick={handleClose} component={RouterLink} to="/taquillas">Administrar taquillas</MenuItem>
								<MenuItem onClick={handleClose} component={RouterLink} to="/taquillas">Ver notificaciones</MenuItem>
								<MenuItem onClick={handleClose} component={RouterLink} to="/taquillas">Panel de administración</MenuItem>
								<Divider />
								<MenuItem onClick={handleClose} component={RouterLink} to="/">Salir</MenuItem>

							</Menu>
						</div>
					)}
					<FormGroup className={classes.appBar}>
						<FormControlLabel
							control={<Switch checked={auth} onChange={handleChange} aria-label="login switch" />}
							label={auth ? 'Admin' : 'Admin'}
						/>
					</FormGroup>
				</Toolbar>
			</AppBar>
		</div>
	);
}
