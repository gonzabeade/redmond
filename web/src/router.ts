// Generouted, changes to this file will be overriden
/* eslint-disable */

import { components, hooks, utils } from '@generouted/react-router/client'

export type Path =
  | `/`
  | `/dashboard`
  | `/qr`
  | `/register`
  | `/transaction/:id`
  | `/transfer/:id?`

export type Params = {
  '/transaction/:id': { id: string }
  '/transfer/:id?': { id?: string }
}

export type ModalPath = never

export const { Link, Navigate } = components<Path, Params>()
export const { useModals, useNavigate, useParams } = hooks<Path, Params, ModalPath>()
export const { redirect } = utils<Path, Params>()
