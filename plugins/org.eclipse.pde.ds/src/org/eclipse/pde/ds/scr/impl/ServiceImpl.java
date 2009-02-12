/**
 * Copyright (c) 2009 Anyware Technologies and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Anyware Technologies - initial API and implementation
 *
 * $Id: ServiceImpl.java,v 1.1 2009/02/12 17:06:39 bcabe Exp $
 */
package org.eclipse.pde.ds.scr.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.pde.ds.scr.Provide;
import org.eclipse.pde.ds.scr.ScrPackage;
import org.eclipse.pde.ds.scr.Service;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Service</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.pde.ds.scr.impl.ServiceImpl#getProvide <em>Provide</em>}</li>
 *   <li>{@link org.eclipse.pde.ds.scr.impl.ServiceImpl#isServicefactory <em>Servicefactory</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ServiceImpl extends EObjectImpl implements Service {
	/**
	 * The cached value of the '{@link #getProvide() <em>Provide</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProvide()
	 * @generated
	 * @ordered
	 */
	protected EList<Provide> provide;

	/**
	 * The default value of the '{@link #isServicefactory() <em>Servicefactory</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isServicefactory()
	 * @generated
	 * @ordered
	 */
	protected static final boolean SERVICEFACTORY_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isServicefactory() <em>Servicefactory</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isServicefactory()
	 * @generated
	 * @ordered
	 */
	protected boolean servicefactory = SERVICEFACTORY_EDEFAULT;

	/**
	 * This is true if the Servicefactory attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean servicefactoryESet;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ServiceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ScrPackage.Literals.SERVICE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Provide> getProvide() {
		if (provide == null) {
			provide = new EObjectContainmentEList<Provide>(Provide.class, this, ScrPackage.SERVICE__PROVIDE);
		}
		return provide;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isServicefactory() {
		return servicefactory;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setServicefactory(boolean newServicefactory) {
		boolean oldServicefactory = servicefactory;
		servicefactory = newServicefactory;
		boolean oldServicefactoryESet = servicefactoryESet;
		servicefactoryESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ScrPackage.SERVICE__SERVICEFACTORY, oldServicefactory, servicefactory, !oldServicefactoryESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetServicefactory() {
		boolean oldServicefactory = servicefactory;
		boolean oldServicefactoryESet = servicefactoryESet;
		servicefactory = SERVICEFACTORY_EDEFAULT;
		servicefactoryESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, ScrPackage.SERVICE__SERVICEFACTORY, oldServicefactory, SERVICEFACTORY_EDEFAULT, oldServicefactoryESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetServicefactory() {
		return servicefactoryESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ScrPackage.SERVICE__PROVIDE:
				return ((InternalEList<?>)getProvide()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ScrPackage.SERVICE__PROVIDE:
				return getProvide();
			case ScrPackage.SERVICE__SERVICEFACTORY:
				return isServicefactory();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case ScrPackage.SERVICE__PROVIDE:
				getProvide().clear();
				getProvide().addAll((Collection<? extends Provide>)newValue);
				return;
			case ScrPackage.SERVICE__SERVICEFACTORY:
				setServicefactory((Boolean)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case ScrPackage.SERVICE__PROVIDE:
				getProvide().clear();
				return;
			case ScrPackage.SERVICE__SERVICEFACTORY:
				unsetServicefactory();
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case ScrPackage.SERVICE__PROVIDE:
				return provide != null && !provide.isEmpty();
			case ScrPackage.SERVICE__SERVICEFACTORY:
				return isSetServicefactory();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (servicefactory: ");
		if (servicefactoryESet) result.append(servicefactory); else result.append("<unset>");
		result.append(')');
		return result.toString();
	}

} //ServiceImpl
