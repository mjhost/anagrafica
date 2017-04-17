import React from 'react';
import PropTypes from 'prop-types';
import {Navbar, Nav, NavItem, NavDropdown, MenuItem} from 'react-bootstrap';
import {Badge} from 'react-bootstrap';
import {Link} from 'react-router-dom';
import { withRouter } from 'react-router';

import LinkContainer from 'containers/LinkContainer';


class Header extends React.Component {
	constructor(props){
		super(props);
		this.handleLink = this.handleLink.bind(this);
	}

	handleLink(route){
		this.props.history.push(route);
	}

	render() {
		return (
			<Navbar>
				<Navbar.Header>
					<Navbar.Brand>
						<Link to="/">Registro Parrocchiani</Link>
					</Navbar.Brand>
					<Navbar.Toggle />
				</Navbar.Header>
				<Navbar.Collapse>
					<Nav>
						<NavDropdown eventKey={1} title="Amministrazione" id="nav-admin">
							<LinkContainer to="/person/new">
								<MenuItem eventKey={1.1}>Aggiungi persona</MenuItem>
							</LinkContainer>
						</NavDropdown>
					</Nav>
					<Nav pullRight>
						<LinkContainer to="/visits">
							<NavItem eventKey={2}>
								Visite programmate
								{} <Badge>5</Badge>
							</NavItem>
						</LinkContainer>
					</Nav>
				</Navbar.Collapse>
			</Navbar>
		);
	}
}

Header.propTypes = {
	history: PropTypes.object.isRequired
};

export default withRouter(Header);
/*
  <Navbar inverse collapseOnSelect>
    <Navbar.Header>
      <Navbar.Brand>
        <a href="#">React-Bootstrap</a>
      </Navbar.Brand>
      <Navbar.Toggle />
    </Navbar.Header>
    <Navbar.Collapse>
      <Nav>
        <NavItem eventKey={1} href="#">Link</NavItem>
        <NavItem eventKey={2} href="#">Link</NavItem>
        <NavDropdown eventKey={3} title="Dropdown" id="basic-nav-dropdown">
          <MenuItem eventKey={3.1}>Action</MenuItem>
          <MenuItem eventKey={3.2}>Another action</MenuItem>
          <MenuItem eventKey={3.3}>Something else here</MenuItem>
          <MenuItem divider />
          <MenuItem eventKey={3.3}>Separated link</MenuItem>
        </NavDropdown>
      </Nav>
      <Nav pullRight>
        <NavItem eventKey={1} href="#">Link Right</NavItem>
        <NavItem eventKey={2} href="#">Link Right</NavItem>
      </Nav>
    </Navbar.Collapse>
  </Navbar>
 */