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
 * $Id: ComponentImpl.java,v 1.5 2009/07/07 21:52:30 bcabe Exp $
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
import org.eclipse.emf.ecore.util.BasicFeatureMap;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.pde.ds.scr.Component;
import org.eclipse.pde.ds.scr.ConfigurationPolicy;
import org.eclipse.pde.ds.scr.Implementation;
import org.eclipse.pde.ds.scr.Properties;
import org.eclipse.pde.ds.scr.Property;
import org.eclipse.pde.ds.scr.Reference;
import org.eclipse.pde.ds.scr.ScrPackage;
import org.eclipse.pde.ds.scr.Service;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Component</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.pde.ds.scr.impl.ComponentImpl#getMixed <em>Mixed</em>}</li>
 *   <li>{@link org.eclipse.pde.ds.scr.impl.ComponentImpl#isEnabled <em>Enabled</em>}</li>
 *   <li>{@link org.eclipse.pde.ds.scr.impl.ComponentImpl#getFactory <em>Factory</em>}</li>
 *   <li>{@link org.eclipse.pde.ds.scr.impl.ComponentImpl#isImmediate <em>Immediate</em>}</li>
 *   <li>{@link org.eclipse.pde.ds.scr.impl.ComponentImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.pde.ds.scr.impl.ComponentImpl#getActivate <em>Activate</em>}</li>
 *   <li>{@link org.eclipse.pde.ds.scr.impl.ComponentImpl#getDeactivate <em>Deactivate</em>}</li>
 *   <li>{@link org.eclipse.pde.ds.scr.impl.ComponentImpl#getModified <em>Modified</em>}</li>
 *   <li>{@link org.eclipse.pde.ds.scr.impl.ComponentImpl#getConfigurationPolicy <em>Configuration Policy</em>}</li>
 *   <li>{@link org.eclipse.pde.ds.scr.impl.ComponentImpl#getImplementation <em>Implementation</em>}</li>
 *   <li>{@link org.eclipse.pde.ds.scr.impl.ComponentImpl#getAllProperties <em>All Properties</em>}</li>
 *   <li>{@link org.eclipse.pde.ds.scr.impl.ComponentImpl#getProperty <em>Property</em>}</li>
 *   <li>{@link org.eclipse.pde.ds.scr.impl.ComponentImpl#getProperties <em>Properties</em>}</li>
 *   <li>{@link org.eclipse.pde.ds.scr.impl.ComponentImpl#getService <em>Service</em>}</li>
 *   <li>{@link org.eclipse.pde.ds.scr.impl.ComponentImpl#getReference <em>Reference</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ComponentImpl extends EObjectImpl implements Component {
	/**
	 * The cached value of the '{@link #getMixed() <em>Mixed</em>}' attribute list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getMixed()
	 * @generated
	 * @ordered
	 */
	protected FeatureMap mixed;

	/**
	 * The default value of the '{@link #isEnabled() <em>Enabled</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #isEnabled()
	 * @generated
	 * @ordered
	 */
	protected static final boolean ENABLED_EDEFAULT = true;

	/**
	 * The cached value of the '{@link #isEnabled() <em>Enabled</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #isEnabled()
	 * @generated
	 * @ordered
	 */
	protected boolean enabled = ENABLED_EDEFAULT;

	/**
	 * This is true if the Enabled attribute has been set.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean enabledESet;

	/**
	 * The default value of the '{@link #getFactory() <em>Factory</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getFactory()
	 * @generated
	 * @ordered
	 */
	protected static final String FACTORY_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getFactory() <em>Factory</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getFactory()
	 * @generated
	 * @ordered
	 */
	protected String factory = FACTORY_EDEFAULT;

	/**
	 * The default value of the '{@link #isImmediate() <em>Immediate</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #isImmediate()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IMMEDIATE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isImmediate() <em>Immediate</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #isImmediate()
	 * @generated
	 * @ordered
	 */
	protected boolean immediate = IMMEDIATE_EDEFAULT;

	/**
	 * This is true if the Immediate attribute has been set.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean immediateESet;

	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getActivate() <em>Activate</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getActivate()
	 * @generated
	 * @ordered
	 */
	protected static final String ACTIVATE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getActivate() <em>Activate</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getActivate()
	 * @generated
	 * @ordered
	 */
	protected String activate = ACTIVATE_EDEFAULT;

	/**
	 * The default value of the '{@link #getDeactivate() <em>Deactivate</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getDeactivate()
	 * @generated
	 * @ordered
	 */
	protected static final String DEACTIVATE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDeactivate() <em>Deactivate</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getDeactivate()
	 * @generated
	 * @ordered
	 */
	protected String deactivate = DEACTIVATE_EDEFAULT;

	/**
	 * The default value of the '{@link #getModified() <em>Modified</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getModified()
	 * @generated
	 * @ordered
	 */
	protected static final String MODIFIED_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getModified() <em>Modified</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getModified()
	 * @generated
	 * @ordered
	 */
	protected String modified = MODIFIED_EDEFAULT;

	/**
	 * The default value of the '{@link #getConfigurationPolicy() <em>Configuration Policy</em>}' attribute.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @see #getConfigurationPolicy()
	 * @generated
	 * @ordered
	 */
	protected static final ConfigurationPolicy CONFIGURATION_POLICY_EDEFAULT = ConfigurationPolicy.OPTIONAL;

	/**
	 * The cached value of the '{@link #getConfigurationPolicy() <em>Configuration Policy</em>}' attribute.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @see #getConfigurationPolicy()
	 * @generated
	 * @ordered
	 */
	protected ConfigurationPolicy configurationPolicy = CONFIGURATION_POLICY_EDEFAULT;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected ComponentImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ScrPackage.Literals.COMPONENT;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isEnabled() {
		return enabled;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setEnabled(boolean newEnabled) {
		boolean oldEnabled = enabled;
		enabled = newEnabled;
		boolean oldEnabledESet = enabledESet;
		enabledESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ScrPackage.COMPONENT__ENABLED, oldEnabled, enabled, !oldEnabledESet));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetEnabled() {
		boolean oldEnabled = enabled;
		boolean oldEnabledESet = enabledESet;
		enabled = ENABLED_EDEFAULT;
		enabledESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, ScrPackage.COMPONENT__ENABLED, oldEnabled, ENABLED_EDEFAULT, oldEnabledESet));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetEnabled() {
		return enabledESet;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public String getFactory() {
		return factory;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setFactory(String newFactory) {
		String oldFactory = factory;
		factory = newFactory;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ScrPackage.COMPONENT__FACTORY, oldFactory, factory));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isImmediate() {
		return immediate;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setImmediate(boolean newImmediate) {
		boolean oldImmediate = immediate;
		immediate = newImmediate;
		boolean oldImmediateESet = immediateESet;
		immediateESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ScrPackage.COMPONENT__IMMEDIATE, oldImmediate, immediate, !oldImmediateESet));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetImmediate() {
		boolean oldImmediate = immediate;
		boolean oldImmediateESet = immediateESet;
		immediate = IMMEDIATE_EDEFAULT;
		immediateESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, ScrPackage.COMPONENT__IMMEDIATE, oldImmediate, IMMEDIATE_EDEFAULT, oldImmediateESet));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetImmediate() {
		return immediateESet;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ScrPackage.COMPONENT__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public String getActivate() {
		return activate;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setActivate(String newActivate) {
		String oldActivate = activate;
		activate = newActivate;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ScrPackage.COMPONENT__ACTIVATE, oldActivate, activate));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public String getDeactivate() {
		return deactivate;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setDeactivate(String newDeactivate) {
		String oldDeactivate = deactivate;
		deactivate = newDeactivate;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ScrPackage.COMPONENT__DEACTIVATE, oldDeactivate, deactivate));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getModified() {
		return modified;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setModified(String newModified) {
		String oldModified = modified;
		modified = newModified;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ScrPackage.COMPONENT__MODIFIED, oldModified, modified));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public ConfigurationPolicy getConfigurationPolicy() {
		return configurationPolicy;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setConfigurationPolicy(
			ConfigurationPolicy newConfigurationPolicy) {
		ConfigurationPolicy oldConfigurationPolicy = configurationPolicy;
		configurationPolicy = newConfigurationPolicy == null ? CONFIGURATION_POLICY_EDEFAULT : newConfigurationPolicy;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ScrPackage.COMPONENT__CONFIGURATION_POLICY, oldConfigurationPolicy, configurationPolicy));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Implementation getImplementation() {
		return (Implementation)getMixed().get(ScrPackage.Literals.COMPONENT__IMPLEMENTATION, true);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetImplementation(
			Implementation newImplementation, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ScrPackage.Literals.COMPONENT__IMPLEMENTATION, newImplementation, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setImplementation(Implementation newImplementation) {
		((FeatureMap.Internal)getMixed()).set(ScrPackage.Literals.COMPONENT__IMPLEMENTATION, newImplementation);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public FeatureMap getAllProperties() {
		return (FeatureMap)getMixed().<FeatureMap.Entry>list(ScrPackage.Literals.COMPONENT__ALL_PROPERTIES);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Property> getProperty() {
		return getAllProperties().list(ScrPackage.Literals.COMPONENT__PROPERTY);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Properties> getProperties() {
		return getAllProperties().list(ScrPackage.Literals.COMPONENT__PROPERTIES);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public Service getService() {
		return (Service)getMixed().get(ScrPackage.Literals.COMPONENT__SERVICE, true);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetService(Service newService,
			NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ScrPackage.Literals.COMPONENT__SERVICE, newService, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setService(Service newService) {
		((FeatureMap.Internal)getMixed()).set(ScrPackage.Literals.COMPONENT__SERVICE, newService);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Reference> getReference() {
		return getMixed().list(ScrPackage.Literals.COMPONENT__REFERENCE);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public FeatureMap getMixed() {
		if (mixed == null) {
			mixed = new BasicFeatureMap(this, ScrPackage.COMPONENT__MIXED);
		}
		return mixed;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd,
			int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ScrPackage.COMPONENT__MIXED:
				return ((InternalEList<?>)getMixed()).basicRemove(otherEnd, msgs);
			case ScrPackage.COMPONENT__IMPLEMENTATION:
				return basicSetImplementation(null, msgs);
			case ScrPackage.COMPONENT__ALL_PROPERTIES:
				return ((InternalEList<?>)getAllProperties()).basicRemove(otherEnd, msgs);
			case ScrPackage.COMPONENT__PROPERTY:
				return ((InternalEList<?>)getProperty()).basicRemove(otherEnd, msgs);
			case ScrPackage.COMPONENT__PROPERTIES:
				return ((InternalEList<?>)getProperties()).basicRemove(otherEnd, msgs);
			case ScrPackage.COMPONENT__SERVICE:
				return basicSetService(null, msgs);
			case ScrPackage.COMPONENT__REFERENCE:
				return ((InternalEList<?>)getReference()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ScrPackage.COMPONENT__MIXED:
				if (coreType) return getMixed();
				return ((FeatureMap.Internal)getMixed()).getWrapper();
			case ScrPackage.COMPONENT__ENABLED:
				return isEnabled();
			case ScrPackage.COMPONENT__FACTORY:
				return getFactory();
			case ScrPackage.COMPONENT__IMMEDIATE:
				return isImmediate();
			case ScrPackage.COMPONENT__NAME:
				return getName();
			case ScrPackage.COMPONENT__ACTIVATE:
				return getActivate();
			case ScrPackage.COMPONENT__DEACTIVATE:
				return getDeactivate();
			case ScrPackage.COMPONENT__MODIFIED:
				return getModified();
			case ScrPackage.COMPONENT__CONFIGURATION_POLICY:
				return getConfigurationPolicy();
			case ScrPackage.COMPONENT__IMPLEMENTATION:
				return getImplementation();
			case ScrPackage.COMPONENT__ALL_PROPERTIES:
				if (coreType) return getAllProperties();
				return ((FeatureMap.Internal)getAllProperties()).getWrapper();
			case ScrPackage.COMPONENT__PROPERTY:
				return getProperty();
			case ScrPackage.COMPONENT__PROPERTIES:
				return getProperties();
			case ScrPackage.COMPONENT__SERVICE:
				return getService();
			case ScrPackage.COMPONENT__REFERENCE:
				return getReference();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case ScrPackage.COMPONENT__MIXED:
				((FeatureMap.Internal)getMixed()).set(newValue);
				return;
			case ScrPackage.COMPONENT__ENABLED:
				setEnabled((Boolean)newValue);
				return;
			case ScrPackage.COMPONENT__FACTORY:
				setFactory((String)newValue);
				return;
			case ScrPackage.COMPONENT__IMMEDIATE:
				setImmediate((Boolean)newValue);
				return;
			case ScrPackage.COMPONENT__NAME:
				setName((String)newValue);
				return;
			case ScrPackage.COMPONENT__ACTIVATE:
				setActivate((String)newValue);
				return;
			case ScrPackage.COMPONENT__DEACTIVATE:
				setDeactivate((String)newValue);
				return;
			case ScrPackage.COMPONENT__MODIFIED:
				setModified((String)newValue);
				return;
			case ScrPackage.COMPONENT__CONFIGURATION_POLICY:
				setConfigurationPolicy((ConfigurationPolicy)newValue);
				return;
			case ScrPackage.COMPONENT__IMPLEMENTATION:
				setImplementation((Implementation)newValue);
				return;
			case ScrPackage.COMPONENT__ALL_PROPERTIES:
				((FeatureMap.Internal)getAllProperties()).set(newValue);
				return;
			case ScrPackage.COMPONENT__PROPERTY:
				getProperty().clear();
				getProperty().addAll((Collection<? extends Property>)newValue);
				return;
			case ScrPackage.COMPONENT__PROPERTIES:
				getProperties().clear();
				getProperties().addAll((Collection<? extends Properties>)newValue);
				return;
			case ScrPackage.COMPONENT__SERVICE:
				setService((Service)newValue);
				return;
			case ScrPackage.COMPONENT__REFERENCE:
				getReference().clear();
				getReference().addAll((Collection<? extends Reference>)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case ScrPackage.COMPONENT__MIXED:
				getMixed().clear();
				return;
			case ScrPackage.COMPONENT__ENABLED:
				unsetEnabled();
				return;
			case ScrPackage.COMPONENT__FACTORY:
				setFactory(FACTORY_EDEFAULT);
				return;
			case ScrPackage.COMPONENT__IMMEDIATE:
				unsetImmediate();
				return;
			case ScrPackage.COMPONENT__NAME:
				setName(NAME_EDEFAULT);
				return;
			case ScrPackage.COMPONENT__ACTIVATE:
				setActivate(ACTIVATE_EDEFAULT);
				return;
			case ScrPackage.COMPONENT__DEACTIVATE:
				setDeactivate(DEACTIVATE_EDEFAULT);
				return;
			case ScrPackage.COMPONENT__MODIFIED:
				setModified(MODIFIED_EDEFAULT);
				return;
			case ScrPackage.COMPONENT__CONFIGURATION_POLICY:
				setConfigurationPolicy(CONFIGURATION_POLICY_EDEFAULT);
				return;
			case ScrPackage.COMPONENT__IMPLEMENTATION:
				setImplementation((Implementation)null);
				return;
			case ScrPackage.COMPONENT__ALL_PROPERTIES:
				getAllProperties().clear();
				return;
			case ScrPackage.COMPONENT__PROPERTY:
				getProperty().clear();
				return;
			case ScrPackage.COMPONENT__PROPERTIES:
				getProperties().clear();
				return;
			case ScrPackage.COMPONENT__SERVICE:
				setService((Service)null);
				return;
			case ScrPackage.COMPONENT__REFERENCE:
				getReference().clear();
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case ScrPackage.COMPONENT__MIXED:
				return mixed != null && !mixed.isEmpty();
			case ScrPackage.COMPONENT__ENABLED:
				return isSetEnabled();
			case ScrPackage.COMPONENT__FACTORY:
				return FACTORY_EDEFAULT == null ? factory != null : !FACTORY_EDEFAULT.equals(factory);
			case ScrPackage.COMPONENT__IMMEDIATE:
				return isSetImmediate();
			case ScrPackage.COMPONENT__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case ScrPackage.COMPONENT__ACTIVATE:
				return ACTIVATE_EDEFAULT == null ? activate != null : !ACTIVATE_EDEFAULT.equals(activate);
			case ScrPackage.COMPONENT__DEACTIVATE:
				return DEACTIVATE_EDEFAULT == null ? deactivate != null : !DEACTIVATE_EDEFAULT.equals(deactivate);
			case ScrPackage.COMPONENT__MODIFIED:
				return MODIFIED_EDEFAULT == null ? modified != null : !MODIFIED_EDEFAULT.equals(modified);
			case ScrPackage.COMPONENT__CONFIGURATION_POLICY:
				return configurationPolicy != CONFIGURATION_POLICY_EDEFAULT;
			case ScrPackage.COMPONENT__IMPLEMENTATION:
				return getImplementation() != null;
			case ScrPackage.COMPONENT__ALL_PROPERTIES:
				return !getAllProperties().isEmpty();
			case ScrPackage.COMPONENT__PROPERTY:
				return !getProperty().isEmpty();
			case ScrPackage.COMPONENT__PROPERTIES:
				return !getProperties().isEmpty();
			case ScrPackage.COMPONENT__SERVICE:
				return getService() != null;
			case ScrPackage.COMPONENT__REFERENCE:
				return !getReference().isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (mixed: ");
		result.append(mixed);
		result.append(", enabled: ");
		if (enabledESet) result.append(enabled); else result.append("<unset>");
		result.append(", factory: ");
		result.append(factory);
		result.append(", immediate: ");
		if (immediateESet) result.append(immediate); else result.append("<unset>");
		result.append(", name: ");
		result.append(name);
		result.append(", activate: ");
		result.append(activate);
		result.append(", deactivate: ");
		result.append(deactivate);
		result.append(", modified: ");
		result.append(modified);
		result.append(", configurationPolicy: ");
		result.append(configurationPolicy);
		result.append(')');
		return result.toString();
	}

} // ComponentImpl
